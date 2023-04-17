import React from 'react';
import { Link } from 'react-router-dom';
import Rating from './Rating';



//representational
export default function Product(props) {
  const { product} = props;
  const newTo = { 
    pathname: `/product/${product._id}`, 
    param1: props.curDate
  };
  return (
    <div key={product._id} className="card">
        <Link to={`/product/${product._id}`}>
        <img className="medium" src={product.images} alt={product.name} />
        </Link>
      <div className="card-body">
      <Link to={`/product/${product._id}`}>  
          <h2>{product.name}</h2>
      </Link>
          <h2 className="h2Home">Type:{product.brand}</h2>
          <h2 className="h2Home">Category:{product.category}</h2>
          <Rating
          rating={product.rating}
          numReviews={product.numReviews}
        ></Rating>
        <div className="price">₹{product.price}</div>
        <Link to={newTo}>
        <button style={{width:'100%',margin:'0'}}type="button" className="primary" >Book</button>
        </Link>
      </div>
    </div>
  );
}
