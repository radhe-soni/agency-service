import {post} from "axios";

export const registerAPI = (payload)=>{
    return post('/register',payload).then((response)=>{
        console.log(response);
      })
}