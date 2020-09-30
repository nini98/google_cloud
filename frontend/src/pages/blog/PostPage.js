import React from "react";
import {Header, BlogTemplate} from 'components/blog';
import PostContainer from 'container/blog/PostContainer'

const PostPage = ({match}) => {
    const { postid } = match.params;
    return (
      <BlogTemplate header={<Header/>}>
        <PostContainer id={postid}/>
      </BlogTemplate>
    );
  };

export default PostPage;