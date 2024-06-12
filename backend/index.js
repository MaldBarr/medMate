import express from 'express';
import cors from 'cors';

import db from './database/db.js';

import router from './routes/router.js';

const app = express();

app.use(cors());
app.use(express.json());

app.use('/usuario', router);
app.use('/formato_medicina', router);
app.use('/medicamentos', router);
app.use('/frecuencias', router);
app.use('/horas', router);
app.use('/recordatorio', router);
app.use('/recordatorio_hora_medica', router);
/*app.use('/', (req, res) => {
    res.send('Hello World');
}
);*/

try
{
    await db.authenticate();
    console.log('Connection has been established successfully.');
}   
catch (error)
{
    console.error('Unable to connect to the database:', error);
}

app.listen(3000, () => {
    console.log('Server is running on port 3000');
});
