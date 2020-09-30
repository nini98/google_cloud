import axios from "axios";
import {Storage} from 'api/storage';

const API_BASE_URL = '';

//Post 관련 API
export const getPost = (blogid,postId) => axios.get(`${API_BASE_URL}/blog/${blogid}/post/${postId}`);
export const getPosts = (blogid,page,size) => axios.get(`${API_BASE_URL}/blog/${blogid}?page=${page}&size=${size}`);
export const writePost = (title, content) => axios.post(`${API_BASE_URL}/blog/postwrite`, {title,content},
    {
        headers: {
        Authorization: getToken()
        }
    }
);
export const modifiedPost = (id,title, content) => axios.post(`${API_BASE_URL}/blog/blog-post/${id}`,{id,title, content},
    {
        headers: {
        Authorization: getToken()
        }
    }
);
export const deletePost = (id) => axios.post(`${API_BASE_URL}/blog/blog-post/${id}`,id,
    {
        headers: {
        Authorization: getToken()
        }
    }  
);

//Member 관련 API
export const login = (member) => axios.post(`${API_BASE_URL}/blog/Auth/signIn`,member);
export const logout = () => axios.post(`${API_BASE_URL}/blog/Auth/logout`,{},
    {
        headers: {
        Authorization: getToken()
        }
    }
);
export const getMember = () => axios.post(`${API_BASE_URL}/blog/Auth/info`,{},
    {
        headers: {
        Authorization: getToken()
        }
    }
);
export const modifiedMember = (id) => axios.post(`${API_BASE_URL}/member/info`,{},
    {
        headers: {
        Authorization: getToken()
        }
    }
);
export const deleteMember = (id) => axios.post(`${API_BASE_URL}/member/info`,{},
    {
        headers: {
        Authorization: getToken()
        }
    }
);
export const register = (member) => axios.post(`${API_BASE_URL}/blog/Auth/signUp`,member);

//blog 관련 API
export const getBlog = (id) => axios.get(`${API_BASE_URL}/blog/info/${id}`);

//Home 관련 API
export const searchBlog = (search) => axios.get(`${API_BASE_URL}/blog/${search}`);
export const getCategory = () => axios.get(`${API_BASE_URL}/blog/category`);
export const recommandPost = () => axios.get(`${API_BASE_URL}/blog/blog-post/views`);
export const recentPost = () => axios.get(`${API_BASE_URL}/blog/blog-post/recent`);


const getToken = () => {
    const token = Storage.local.get('__AUTH__') || Storage.session.get('__AUTH__');
    
    return `Bearer ${token}`
};