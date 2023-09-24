import axios, { AxiosResponse, InternalAxiosRequestConfig } from "axios";

export const api = axios.create({
  baseURL: "http://localhost:8080/auth",
  headers: {
    "Content-Type": "application/json",
  },
});

api.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    return config;
  },
  (error): unknown => {
    return Promise.reject(error);
  }
);

api.interceptors.response.use(
  (config: AxiosResponse<unknown, unknown>) => {
    return config;
  },
  (error): unknown => {
    return Promise.reject(error);
  }
);
