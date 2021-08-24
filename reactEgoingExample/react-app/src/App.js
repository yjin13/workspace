import React, { Component } from 'react';
import Toc from "./components/Toc";
import ReadContent from "./components/ReadContent";
import CreateContent from "./components/CreateContent";
import UpdateContent from "./components/UpdateContent";
import Subject from "./components/Subject";
import Control from "./components/Control";
import './App.css';


// class 방식
class App extends Component {
  
  // conponent 초기화 (render() 전, 제일 먼저 실행)
  // props나 state 값이 바뀌면 render()가 재실행(화면 다시 그림)
  constructor(props) {
    super(props);
    this.max_content_id = 3;
    this.state = { // state 값 초기화
      mode: "read",
      selected_content_id: 2,
      subject: {
        title: "WEB", sub: "world wide web!"
      },
      welcome: {
        title: "Welcome", desc: "Hello, React!"
      },
      contents: [
        {id: 1, title: "HTML", desc: "HTML is for information."},
        {id: 2, title: "CSS", desc: "CSS is for design."},
        {id: 3, title: "JavaScript", desc: "JavaScript is for interactive."}
      ]
    };
  }

  getReadContent() {
    var i = 0;
    while (i < this.state.contents.length) {
      var data = this.state.contents[i];
      if (data.id === this.state.selected_content_id) {
        return data;
      }
      i++;
    }
  }

  // content 영역 교체
  getContent() {
    var _title, _desc, _article = null;
    if (this.state.mode === "welcome") {
      _title = this.state.welcome.title;
      _desc = this.state.welcome.desc;
      _article = <ReadContent title={_title} desc={_desc}></ReadContent>;

    } else if (this.state.mode === "read") {
      var _content = this.getReadContent();
      _article = <ReadContent title={_content.title} desc={_content.desc}></ReadContent>;

    } else if (this.state.mode === "create") {
      _article = <CreateContent 
        onSubmit={function(_title, _desc) {
          this.max_content_id++;
          // this.state.contents.push({id: this.max_content_id, title: _title, desc: _desc}); // 원본 값 변경 (성능 개선에 좋지 않음)
          var _contents = this.state.contents.concat({id: this.max_content_id, title: _title, desc: _desc}); // 새로운 값으로 대체 (유지보수에 좋음)
          this.setState({
            contents: _contents
          });
        }.bind(this)}></CreateContent>;

    } else if (this.state.mode === "update") {
      _content = this.getReadContent();
      _article = <UpdateContent data={_content}
        onSubmit={function(_id, _title, _desc) {
          var _contents = Array.from(this.state.contents); // 복사
          var i = 0;
          while (i < _contents.length) {
            if(_contents[i].id === _id) {
              _contents[i] = {id: _id, title: _title, desc: _desc};
              break;
            }
            i++;
          }
          this.setState({
            contents: _contents,
            mode: "read"
          });
        }.bind(this)}></UpdateContent>;
    }

    return _article;
  }

  render() {
    return (
      <div className="App">
        <Subject 
          title={this.state.subject.title} 
          sub={this.state.subject.sub}
          onChangePage={function() {
            this.setState({mode: "welcome"});
          }.bind(this)}
        >
        </Subject>
        <Toc 
          data={this.state.contents}
          onChangePage={function(id) {
            this.setState({
              mode: "read",
              selected_content_id: Number(id)
            });
          }.bind(this)}
        >
        </Toc>
        <Control 
          onChangeMode={function(_mode) {
            if (_mode === "delete") {
              if (window.confirm("really?")) {
                var _contents = Array.from(this.state.contents);
                var i = 0;
                while (i < this.state.contents.length) {
                  if (_contents[i].id === this.state.selected_content_id) {
                    _contents.splice(i, 1);
                    break;
                  }
                  i++;
                }
                this.setState({
                  mode: "welcome",
                  contents: _contents
                });
                alert("fin.");
              }
            } else {
              this.setState({
                mode: _mode
              });
            }
          }.bind(this)}
        >
        </Control>
        {this.getContent()}
      </div>
    );
  }
}

export default App;