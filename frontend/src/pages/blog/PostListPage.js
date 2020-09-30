import React from "react";
import {Header, BlogTemplate} from 'components/blog';
import PostListContainer from 'container/blog/PostListContainer'

const PostListPage = ({match}) => {

    
    const { id } = match.params;

    return (
    <BlogTemplate header={<Header/>}>
        <PostListContainer id={id}/>
    </BlogTemplate>


    );
};

export default PostListPage;