import React, { useState } from 'react';
import {Link} from 'react-router-dom';
import { Button } from '@material-ui/core';
import LoginMenu from 'components/login/LoginMenu';

const Sidenav = ({isAuthenticated, onLogout}) => {

    const [text, setText] = useState('');

    const FindBlog = () => {

    }
    const Onchange = (e) => {
        setText(e.target.value);
    };

    return (
        <div className="container">
            <div className="sidenav">
                <h2>Web_Project Blog</h2>
                <p><LoginMenu  isAuthenticated={isAuthenticated} onLogout={onLogout}/></p>
                <Link to="/about">소개글</Link>
                <p>
                    <input 
                    placeholder="검색할 키워드를 입력하세요."
                    onChange={Onchange}
                    value={text}
                    />
                    <Button variant="contained" color="secondary" onClick={FindBlog}>
                        검색
                    </Button>

                </p>
            </div>
        </div>
    );
};

export default Sidenav;