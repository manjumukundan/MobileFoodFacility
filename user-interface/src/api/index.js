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
    findFoodTrucks(position, count) {
        return instance.get('/findfoodtrucks?lat=' + position.lat + '&lng=' + position.lng
            + '&count=' + count, headers);
    }
};

export default api;
