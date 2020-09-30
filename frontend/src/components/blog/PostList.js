import React from 'react';
import {Button} from '@material-ui/core';
import { PostPreView } from 'components/blog';


const PostList = (props) => {

  const {posts,id,isAuthenticated,currentUser} = props;



  const PostList = ({posts}) => {


    

    return (
      <div>
        {
          posts.map(p =>
            <PostPreView key={p.get("idx")} post={p} />
          )
        }
      </div>
    );

      
    
  }

  const PostWrite = () => {

  }


  return (

    <div>
      {(currentUser.get("idx") == id) && 
      <Button onClick={PostWrite} color="primary">
        New Post
      </Button>
      }
      {posts && <PostList posts={posts}/>}
    </div>
);


};

export default PostList;