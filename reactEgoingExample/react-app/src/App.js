import React, { Component } from 'react';
import Toc from "./components/Toc";
import Content from "./components/Content";
import Subject from "./components/Subject";
import './App.css';


// class 방식
class App extends Component {
  
  // conponent 초기화 (render() 전, 제일 먼저 실행)
  // props나 state 값이 바뀌면 render()가 재실행(화면 다시 그림)
  constructor(props) {
    super(props);
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
    var _title, _desc = null;
    if (this.state.mode === "welcome") {
      _title = this.state.welcome.title;
      _desc = this.state.welcome.desc;
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
        <Content title={_title} desc={_desc}></Content>
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