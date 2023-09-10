import axios from "axios";

export const api = axios.create({
    baseURL: 'http://localhost:8080/auth',
    headers: {
        'Content-Type': 'application/json'
    }
});