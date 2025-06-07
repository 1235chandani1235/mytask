import React from 'react';
import { CssBaseline, AppBar, Toolbar, Typography, Container } from '@mui/material';
import JobList from './components/JobList';

function App() {
  return (
    <>
      <CssBaseline />
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6">
            Job Portal
          </Typography>
        </Toolbar>
      </AppBar>
      <Container>
        <JobList />
      </Container>
    </>
  );
}

export default App;
