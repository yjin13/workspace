import React, { Component } from 'react';
import Toc from "./components/Toc";
import ReadContent from "./components/ReadContent";
import CreateContent from "./components/CreateContent";
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

  // html 결정
  // onClick 등 이벤트 함수가 실행되면 reload됨 -> e.preventDefault()로 막기
  render() {

    // content 영역 교체
    var _title, _desc, _article = null;
    if (this.state.mode === "welcome") {
      _title = this.state.welcome.title;
      _desc = this.state.welcome.desc;
      _article = <ReadContent title={_title} desc={_desc}></ReadContent>;
    } else if (this.state.mode === "read") {
      var i = 0;
      while (i < this.state.contents.length) {
        var data = this.state.contents[i];
        if (data.id === this.state.selected_content_id) {
          _title = data.title;
          _desc = data.desc;
          break;
        }
        i++;
      }
      _article = <ReadContent title={_title} desc={_desc}></ReadContent>;
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
    }
    
    return (
      <div className="App">
        {/*
        <header>
          <h1>
            <a href="/" onClick={function(e) { // this를 못찾으면 bind 설정
              e.preventDefault(); // reload 막기
              this.setState({ // state 값 변경 (this.state.mode = "" 로 바꿀 수 없음: 리액트가 인지하지 못함)
                mode: "welcome"
              });
            }.bind(this)}>{this.state.subject.title}</a>
          </h1>
          {this.state.subject.sub}
        </header>
        */}

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
            this.setState({
              mode: _mode
            });
          }.bind(this)}
        >
        </Control>
        {_article}
      </div>
    );
  }
}

// function 방식
/*
function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}
*/

export default App;