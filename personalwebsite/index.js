import express from "express";
import bodyParser from "body-parser";
import ejs from "ejs";

const app=express();
const port=3000;

app.use(bodyParser.urlencoded({extended:true}));
app.use(express.static("public"));
app.use('/public/images/', express.static('./public/images'));


app.get("/",(req,res)=>{
    res.render("index.ejs");
});

app.get("/skills",(req,res)=>{
    res.render("skills.ejs");
});

app.get("/projects",(req,res)=>{
    res.render("projects.ejs");
});

app.get("/certificates",(req,res)=>{
    res.render("certificates.ejs");
});

app.get("/contacts",(req,res)=>{
    res.render("contacts.ejs");
});

app.listen(port,()=>{
    console.log("The server is running on port 3000");

})