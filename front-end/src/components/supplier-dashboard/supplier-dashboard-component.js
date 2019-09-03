import React from "react";
export const SupplierDashboardComponent = props => {
  const productCard = (
    <div className="card">
      <h5 className="card-header"> Products</h5>
      <div className="card-body"></div>
    </div>
  );
  return <div className="container">{productCard}</div>;
};
