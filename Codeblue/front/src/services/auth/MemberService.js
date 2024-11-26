import axios from "axios";

const baseURL = "http://localhost:8000/api"

// TODO: 회원가입
const insert = (data) => {
    return axios.post(baseURL + `/auth/register`, data);
};

// TODO: 로그인
const login = (data) => {
    return axios.post(baseURL + `/auth/login`, data);
};

// TODO: 로그아웃 : 
const logout = () => {
    localStorage.removeItem("user"); 
};

const MemberService = {
    insert,
    login,
    logout
}

export default MemberService;