import React from 'react';
import { Container } from '@material-ui/core';
import {Category} from 'components/blog';


const PageTemplate = ({ header, children }) => {
  return (
    <div>
      <header>
        {header}
      </header>
      <main>
        <Container maxWidth="false" style={{display: "flex",flexDirection: 'row',}}>
          <div style={{flex:20}}>
            <Category />
          </div>
          <div style={{flex:80}}>
            {children}
          </div>
        </Container>
      </main>
    </div>
  )
};

export default PageTemplate;