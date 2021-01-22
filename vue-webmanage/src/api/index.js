import request from '../utils/request';
const host="http://localhost:8084/"
export const fetchData =(url,method,query) => {
    return request({
        url: host+url,
        method: method,
        headers:{
        	'content-type':'application/json'
        },
        data: JSON.stringify(query)
    });
};
