import { 
  Button, 
  Table, 
  TableBody, 
  TableCell, 
  TableContainer, 
  TableHead, 
  TableRow,
  Paper,
  makeStyles,
  Container,
  TextField,
  Grid
} from '@material-ui/core';
import React, { useState, useEffect } from 'react';
import './App.css';
import axios from 'axios';

interface Contact {
  uuid?: string,
  name: string,
  phoneNumber: string
}

function App() {

  const [ contacts, setContacts ] = useState<Contact[]>([]);
  const [ name, setName ] = useState<string>('');
  const [ phoneNumber, setPhoneNumber ] = useState<string>('');
  const useStyles = makeStyles({
    table: {
      minWidth: 650,
    },
  });

  const classes = useStyles();
  
  useEffect( () => { 
    findAllContacts()
  }, []);



  const api = axios.create({
    baseURL: 'http://localhost:8080',
  });

  const findAllContacts = async () => {
   const { data } = await api.get<Contact[]>('/api/contacts');
   setContacts(data);
  }

  const saveContact = async () => {
    const contact: Contact = { name, phoneNumber};
    await api.post('/api/contacts', contact);
  }

  return (
    <div className="App">
      <Container maxWidth="xl">
       
        
        <Grid container direction="column" spacing={2}>
          <Grid item xs={12}>
            <TextField 
              fullWidth 
              id="name" 
              label="Name" 
              variant="outlined"
              value={name} 
              onChange={event => setName(event.target.value)}
              />
          </Grid>
          <Grid item xs={12}>
            <TextField 
              fullWidth 
              id="phoneNumber" 
              label="Phone Number" 
              variant="outlined"
              value={phoneNumber}
              onChange={event => setPhoneNumber(event.target.value)}
              />
          </Grid>
          <Grid item xs={12}>
            <Button color="primary" variant="contained" onClick={() => saveContact()} >Save</Button>
          </Grid>
        </Grid>
        
        <TableContainer component={Paper}>
          <Table className={classes.table} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>ID</TableCell>
                <TableCell align="left">Name</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {contacts.map((row) => (
                <TableRow key={row.uuid}>
                  <TableCell component="th" scope="row">{row.uuid}</TableCell>
                  <TableCell align="left">{row.name}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
        <Button color="primary" variant="contained" onClick={() => findAllContacts()} >Find</Button>
      </Container>
    </div>
  );
}

export default App;
