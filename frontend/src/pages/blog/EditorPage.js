import React from "react";
import {Header, BlogTemplate} from 'components/blog';
import EditorContainer from 'container/blog/EditorContainer'

const EditorPage = ({match}) => {
    const { id } = match.params;
    return (
      <BlogTemplate header={<Header/>}>
        <EditorContainer id={id}/>
      </BlogTemplate>
    );
  };
  
  export default EditorPage;