var estados = [
	{ text: "activo", id: 1},
	{ text: "cerrado", id: 2}
]; 
var ingenieros = [
	{ text: "Cristian", id: 1},
	{ text: "Esteban", id: 2},
	{ text: "Victor", id: 3}
]; 

var link_tab1 = "jsp/calendario.aspx";
var link_tab2 = "jsp/reporte.aspx";

var tabStripData = [{
            text: "Calendario",
            contentUrl: link_tab1
        },
        {
            text: "Reporte",
            contentUrl: link_tab2
        }];

var proyectos = [ "Herramienta Gestion de Tareas","Investigación preliminar"];
var tareas = [
{
id_tarea:44,
tarea:"Herramienta Gestion de Tareas",
proyecto:"Herramienta Gestion de Tareas",
Integrantes:[1,2,3],
Description:"Analisis",
Start:"\/Date(1412107200000)\/",
End:"\/Date(1412112600000)\/",
fecha_termino_real:"\/Date(1412112600000)\/",
tipo:1,
estado:2,
comentarios:[
{
	id_comentario : 54,
	comentario : "Seguro de Vida con Ahorro Principal, con y sin Ahorro Previsional Voluntario los proyectos y el futuro de la familia de sus clientes, están protegidos. Los Seguros de Vida con Ahorro de Principal son soluciones flexibles que combinan ahorro a largo plazo y protección",
	usuario : "danileruleru@gmail.com",
	fecha : "22/08/2014 10:56:05",
},
{
	id_comentario : 45,
	comentario : "Ahorro: una parte de lo que paga mensualmente se acumula en una cuenta de ahorro (valor póliza), donde su cliente puede elegir entre los Fondos Mutuos ofrecidos por Principal de acuerdo a su perfil de riesgo y horizonte de inversión.",
	usuario : "danileruleru@gmail.com",
	fecha : "22/08/2014 10:56:05",
},
{
	id_comentario : 23,
	comentario : "Es un crédito flexible, que permite pagar una de tres alternativas de dividendo mensual, según su capacidad financiera del momento.Financiamiento hasta el 80% del valor de la propiedadTasa de interés anual fija",
	usuario : "danileruleru@gmail.com",
	fecha : "22/08/2014 10:56:05",
}
],
files: [
        {name: "1256-reporte.xls", size: 721, extension: ".xls"},
        {name: "1257-prueba.jpg", size: 720, extension: ".jpg"},
        {name: "1258-requerimiento.doc", size: 720, extension: ".doc"},
        {name: "1259-correo.msg", size: 720, extension: ".msg"},
    ]
},{
MeetingID:44,
tarea:"Investigación preliminar",
proyecto:"Herramienta Gestion de Tareas",
Integrantes:[1],
Description:"Analisis",
Start:"\/Date(1412107200000)\/",
End:"\/Date(1412112600000)\/",
fecha_termino_real:"\/Date(1412112600000)\/",
IsAllDay:false,
tipo:1,
estado:2,
comentarios:[
{
	id_comentario : 35,
	comentario : "Seguro de Vida con Ahorro Principal, son soluciones flexibles que combinan ahorro a largo plazo y protección",
	usuario : "danileruleru@gmail.com",
	fecha : "22/08/2014 10:56:05",
},
{
	id_comentario : 33,
	comentario : "Ahorro: una parte de lo que paga  acuerdo a su perfil de riesgo y horizonte de inversión.",
	usuario : "danileruleru@gmail.com",
	fecha : "22/08/2014 10:56:05",
},
{
	id_comentario : 18,
	comentario : "Es un crédito flexible, que permopiedadTasa de interés anual fija",
	usuario : "danileruleru@gmail.com",
	fecha : "22/08/2014 10:56:05",
}
],
files: [
       { name: "file1.doc", size: 525, extension: ".doc"}
    ]
},
];
var gantt = [
{
	"ID":1,
	"Title":"Herramienta Gestion de Tareas",
	"ParentID":null,
	"OrderID":0,
	"Start":"\/Date(1409112000000)\/",
	"End":"\/Date(1418094000000)\/",
	"PercentComplete":0.43,
	"Summary":true,
	"Expanded":true},
{   "ID":2,
	"Title":"Analisis",
	"ParentID":1,
	"OrderID":1,
	"Start":"\/Date(1409112000000)\/",
	"End":"\/Date(1412049600000)\/",
	"PercentComplete":0.43,
	"Summary":true,
	"Expanded":true
},
{
	"ID":3,
	"Title":"Investigación preliminar",
	"ParentID":2,
	"OrderID":0,
	"Start":"\/Date(1409112000000)\/",
	"End":"\/Date(1409630400000)\/",
	"PercentComplete":1,
	"Summary":false,
	"Expanded":true},
{
	"ID":4,
	"Title":"Generar prototipo principal",
	"ParentID":2,
	"OrderID":0,
	"Start":"\/Date(1409630400000)\/",
	"End":"\/Date(1410321600000)\/",
	"PercentComplete":0.51,
	"Summary":false,
	"Expanded":true
},
{
	"ID":5,
	"Title":"Especificación de requerimientos",
	"ParentID":2,
	"OrderID":1,
	"Start":"\/Date(1410321600000)\/",
	"End":"\/Date(1411531200000)\/",
	"PercentComplete":0,
	"Summary":false,
	"Expanded":true
},{
	"ID":6,
	"Title":"Generar Carta Gantt",
	"ParentID":2,
	"OrderID":2,
	"Start":"\/Date(1411531200000)\/",
	"End":"\/Date(1411704000000)\/",
	"PercentComplete":0,
	"Summary":false,
	"Expanded":true
},{
	"ID":7,
	"Title":"Generar prototipo final",
	"ParentID":2,
	"OrderID":3,
	"Start":"\/Date(1411704000000)\/",
	"End":"\/Date(1411963200000)\/",
	"PercentComplete":0,
	"Summary":false,
	"Expanded":true
},{
	"ID":8,
	"Title":"Definir herramientas a utilizar y responsables de las tareas",
	"ParentID":2,
	"OrderID":4,
	"Start":"\/Date(1411963200000)\/",
	"End":"\/Date(1412049600000)\/",
	"PercentComplete":0,
	"Summary":false,
	"Expanded":true
},
];


