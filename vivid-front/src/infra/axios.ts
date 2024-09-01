import axios from "axios"

console.log(import.meta.env.VITE_SERVER_BASE_URL)
const _instance = axios.create({
    baseURL: import.meta.env.VITE_SERVER_BASE_URL
});

export { _instance as axios }
