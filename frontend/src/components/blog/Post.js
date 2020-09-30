import React, {Component} from 'react';
import Viewer from '@toast-ui/editor/dist/toastui-editor-viewer';
import 'codemirror/lib/codemirror.css'; // Editor's Dependency Style
import '@toast-ui/editor/dist/toastui-editor-viewer.css';


var toastViewer;

class Post extends Component {


  componentDidMount(){

    const { post } = this.props;

    toastViewer = new Viewer({
        el: document.querySelector('#ViewSection'),
        initialValue: post.get("content")
    });
  };

  render(){

    const { post } = this.props;

      return (
          <div id="toastEditor">
              <h1>{post.get("title")}</h1>
              <div id="ViewSection"></div>
          </div>
      );
  };

};

export default Post;