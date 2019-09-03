import React from "react";
import {
    Form,
    Card,
    FormGroup,
    Label,
    Input,
    CardBody,
    Button
} from "reactstrap";
import { Link } from "react-router-dom";
export default class ProductConfigComponent extends React.Component {
    state = {
        category: "",
        brandName: "",
        genericName: "",
        ratePrice: 0,
        rateBasis: 1,
        availableSizes: [""],
        material: "",
        supplierId: ""
    };
    onChange = event => {
        event.preventDefault();
        const name = event.target.name;
        const value = event.target.value;
        this.setState(
          {
            [name]: value
          }
        );
      };
    addSizes = event => {
        event.preventDefault();
        const name = event.target.name;
        const value = event.target.value.split(',');
        this.setState({
            [name] : value
        });
    }
    render() {
        return (
            <div className="container-fluid d-flex justify-content-center bg-light h-100"> 
            <Card className="shadow mt-5 py-5 w-50">
          <h1 className="text-center text-secondary ">Add Product</h1>
          <CardBody>
                <Form onSubmit={this.onSubmit}>
                    <FormGroup>
                        <Label className="text-muted">Category</Label>
                        <Input
                            type="text"
                            name="category"
                            id="category"
                            onChange={this.onChange}
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label className="text-muted">Brand Name</Label>
                        <Input
                            type="text"
                            name="brandName"
                            id="brandName"
                            onChange={this.onChange}
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label className="text-muted">Generic Name</Label>
                        <Input
                            type="text"
                            name="genericName"
                            id="genericName"
                            onChange={this.onChange}
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label className="text-muted">Generic Name</Label>
                        <Input
                            type="text"
                            name="genericName"
                            id="genericName"
                            onChange={this.onChange}
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label className="text-muted">Rate/(Quantity)</Label>
                        <Input style={{width:"20%"}}
                            type="currency"
                            name="ratePrice"
                            id="ratePrice"
                            value="0.0"
                            onChange={this.onChange}
                        />
                        <Input style={{width:"20%"}}
                            type="number"
                            name="rateBasis"
                            id="rateBasis"
                            value="1"
                            onChange={this.onChange}
                        />
                    </FormGroup>
                    <FormGroup>
                        <Label className="text-muted">Available Sizes</Label>
                        <Input
                            type="text"
                            name="availableSizes"
                            id="availableSizes"
                            onChange={this.addSizes}
                        />
                    </FormGroup>
                </Form>
                </CardBody>
                </Card>
            </div>
        );
    }
}