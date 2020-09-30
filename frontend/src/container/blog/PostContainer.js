import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from "redux";
import * as postActions from "store/modules/post";
import {Post} from 'components/blog';
import { withRouter } from 'react-router-dom';

class PostContainer extends Component {

  getPost = async (userid,postid) => {
    const { PostActions } = this.props;
    try {
      await PostActions.getPost(userid,postid);
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
    const { id,postContents } = this.props;
    console.log(postContents, id);
    if (id) {
      this.getPost(1,id);
    }    
  }


  render() {

    const { post,success } = this.props;

    return (
      <div>
      
        {success && <Post post={post}/>}
      </div>
    );
  }
}

export default connect(
  state => ({
    loading: state.pender.pending["post/GET_POST"],
    error: state.pender.failure["post/GET_POST"],
    success: state.pender.success["post/GET_POST"],
    post: state.post.get("post"),
  }),
  dispatch => ({
    PostActions: bindActionCreators(postActions, dispatch)
  })
)(withRouter(PostContainer));

