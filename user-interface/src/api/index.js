import axios from "axios"

const url = process.env.VUE_APP_API_BASE_URL;

const instance = axios.create({
    baseURL: url
});

const headers = {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin' : '*',
}

const api = {
    findFoodTrucks() {
        return instance.get('/findfoodtrucks', headers);
    }
};

export default api;
