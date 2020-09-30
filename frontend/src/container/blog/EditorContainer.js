import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from "redux";
import * as postActions from "store/modules/post";
import {PostEditor} from 'components/blog';

class EditorContainer extends Component {



  getPost = async (id) => {
    const { PostActions } = this.props;
    try {
      await PostActions.getPost(id);
    } catch (e) {
      console.log("error log :" + e);
    }
  }

  writePost = async (id, title, body) => {
    const { PostActions, history } = this.props;
    try {

      if (id) {
        await PostActions.editPost(id, title, body);      
      } else {
        await PostActions.writePost(title, body);
      }
      
      history.push(`/posts/${this.props.postId}`);

      
    } catch (e) {
      console.log("error log :" + e);
    }
  }

  componentDidMount() {
    const { id } = this.props;
    if (id) {
      this.getPost(id);
    }    
  }


  render() {

    const { id } = this.props;

    return ( 
        <PostEditor writePost={this.writePost} id={id}/>
    );
  }
}

export default connect(
  state => ({
    loading: state.pender.pending["post/GET_POST"],
    error: state.pender.failure["post/GET_POST"],
    success: state.pender.success["post/GET_POST"]
  }),
  dispatch => ({
    PostActions: bindActionCreators(postActions, dispatch)
  })
)(EditorContainer);

