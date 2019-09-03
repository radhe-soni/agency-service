import {post} from "axios";

export const loginAPI = (payload)=>{
    return post('/login',payload).then((response)=>{
        console.log(response);
      })
}