import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import { SupplierDashboardComponent } from '../supplier-dashboard/supplier-dashboard-component';
import { HelloComponent } from '../hello-component';

export const DashboardComponent = ({ user }) => (
  <BrowserRouter>
    <Switch>
      {user === 'supplier' && <Route path='/dashboard/supplier' component={SupplierDashboardComponent} />}
      {user === 'buyer' && <Route path='/dashboard/buyer' component={HelloComponent} />}
      <Route exact path='/dashboard' component={SupplierDashboardComponent} />
    </Switch>
  </BrowserRouter>
);