const express = require('express');
const app = express();
app.all('/server',(request,response) =>{
    response.setHeader('Access-Control-Allow-Origin','*')
    response.send("hello!")
})
app.all("/main", (request, response) => {
    // 设置一个响应头,设置允许跨域
    response.setHeader('Access-Control-Allow-Origin','*')
    // 响应头(所以类型的头都可以接受)
    response.setHeader('Access-Control-Allow-Headers','*')
    // 设置响应体
    const data = {name:'fanqiqi'}
    response.send(JSON.stringify(data))
})
app.listen(8080,()=>{
    console.log('服务已启动！8080端口')
})