import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from "redux";
import * as postActions from "store/modules/post";
import {PostList} from 'components/blog';

class PostListContainer extends Component {

  getPostList = async (page, size) => {
    const { PostActions,id } = this.props;
    try {
      await PostActions.getPostList(id, page, size);     
    } catch (e) {
      console.log("error log :" + e);
    }
  }
  
  componentDidMount() {
    this.getPostList(0, 10);
  }


  render() {

    const { posts,loading,error,success,id,isAuthenticated,currentUser } = this.props;

    return (
      <div>
        { loading && "PostList Loading..." }
        { error && <h1>Server Error!</h1> }
        { success && 
          <PostList posts={posts}
                    id={id}
                    isAuthenticated={isAuthenticated}
                    currentUser={currentUser}
          />
        }
      </div>
    );
  }
}

export default connect(
  state => ({
    loading: state.pender.pending["post/GET_POST_LIST"],
    error: state.pender.failure["post/GET_POST_LIST"],
    success: state.pender.success["post/GET_POST_LIST"],
    posts: state.post.get("posts"),
    isAuthenticated: state.auth.get("isAuthenticated"),
    currentUser: state.auth.get("currentUser")
  }),
  dispatch => ({
    PostActions: bindActionCreators(postActions, dispatch)
  })
)(PostListContainer);

