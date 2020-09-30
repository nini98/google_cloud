import React from 'react';
import parse from 'html-react-parser';
import { Link } from '@material-ui/core';
const PostPreView = ({post}) => {

    var content = new DOMParser().parseFromString(post.get("content"), "text/html").body;
    var description;
    if(content.innerText.length>50)
        description = content.innerText.slice(0,50) + '...';
    else
        description = content.innerText;
    return (
        <div style={{borderTop:"0.5px solid black"}}>
            <Link style={{ textDecoration: 'none'}} href={"/blog/1/post/" + post.get("idx")} color="inherit">
                <h1>{post.get("title")}</h1>
                <div>
                    조회수 {post.get("views")}
                </div>
                <div style={{paddingBottom:'15px'}}>
                    {parse(description)}
                </div>
            </Link>
        </div>
    );
};
export default PostPreView;