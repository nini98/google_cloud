import React, {Component} from 'react';
import Editor from '@toast-ui/editor';
import 'codemirror/lib/codemirror.css'; // Editor's Dependency Style
import '@toast-ui/editor/dist/toastui-editor.css'; // Editor's Style
import {Button, Input} from '@material-ui/core';


var toastEditor;

class PostEditor extends Component {

  constructor(){
    super();
    this.state = {
      text:'',
    };
    this.saveArticle = this.saveArticle.bind(this);
    this.PressEnter = React.createRef();
  };

  componentDidMount(){
      toastEditor = new Editor({
          el: document.querySelector('#editSection'),
          height: '500px',
          initialEditType: 'wysiwyg',
          previewStyle: 'vertical',
          /*hooks: {
            addImageBlobHook: (blob,callback) => {
              const reader = new FileReader();
              reader.onload = e => {

                console.log(e);
                console.log(e.target);
                console.log(e.target.result);
                this.setState(prev => ({
                  images: [...prev.images, e.target.result]
                }))
                callback(e.target.result,'custom text')
              };
              
              reader.readAsDataURL(blob);
            }
          }*/
      });
  };

  saveArticle(){
      const content = toastEditor.getHtml();
      const {writePost, id} = this.props;
      const title = this.state.text;

      console.log(id, title, content);

      var imgNum = document.getElementsByClassName("tui-editor-contents")[1].getElementsByTagName('img').length;

      if(imgNum > 10)
      {
        console.log("이미지가 너무 많습니다.")
      }
      else
      {
        if(title && content)
        {
          if((title.length > 2) && (content.length > 10))
          {
            writePost('',title,content);
          }
          else
          {
            console.log("제목 또는 내용이 너무 짧습니다.")
          }
        }
        else
        {
          console.log("제목 또는 내용이 비어있습니다.")
        }
      }


  };

  Onchange = (e) => {
    this.setState({
      text: e.target.value,
    })
  };

  OnPressNext = (e) => {

    if(e.key === "Enter")
    {
      this.OnFocusEditor();
    }
  };

  OnFocusEditor() {

    document.getElementsByClassName("tui-editor-contents")[1].focus();
  }

  render(){

      return (
          <div id="toastEditor">
              <h1>Post Writer</h1>
              <div id="postTitle">
              <Input 
                    placeholder="제목을 입력하세요."
                    onChange={this.Onchange}
                    value={this.state.text}
                    fullWidth={true}
                    style= {{fontSize:'30px', }}
                    onKeyPress={this.OnPressNext}
                    onBlur={this.OnFocusEditor}
              />
              </div>
              <div id="editSection"></div>
              <Button variant="contained" color="primary" disableElevation onClick={this.saveArticle}>Save</Button>
              {/*<div>
                  <h2>result</h2>
                  <textarea className="tf_result" value={this.state.content} readOnly="readOnly"></textarea>
              </div>*/}
          </div>
      );
  };

};

export default PostEditor;