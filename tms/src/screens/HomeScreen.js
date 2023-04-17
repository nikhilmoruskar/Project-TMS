import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { HeadingScreen } from "./HeadingScreen";
import { listProducts } from '../actions/productActions';
import { savecity } from '../actions/cityAction';
import Product from '../components/Product';
import LoadingBox from '../components/LoadingBox';
import MessageBox from '../components/MessageBox';




export default function HomeScreen() {
  const dispatch = useDispatch();
  const productList = useSelector((state) => state.productList);
  const {loading, error, products } = productList;
  const [source, setSource] = useState("");
  const [destination, setDestination] = useState("");
  var date1 = new Date();
  var year = date1.toLocaleString("default", { year: "numeric" });
  var month = date1.toLocaleString("default", { month: "2-digit" });
  var day = date1.toLocaleString("default", { day: "2-digit" });
  const [date, setDate] = useState(year + "-" + month + "-" + day);
  const maxDate = year + "-" + month + "-" + (+day + 8);
  const minDate = year + "-" + month + "-" + day;

  const cities = useSelector((state) => state.cities);

  useEffect(() => {
    dispatch(listProducts());
    if (cities["cities"]) {
      setSource(cities["cities"]["source"]);
      setDestination(cities["cities"]["destination"]);
    }
  }, [dispatch, cities]);

  let [buses, setBus] = useState(null);

  const searchBusHandler = () => {
    buses = products
      ?.filter((product) => {
        if (
          product.source.toLowerCase() === source.toLowerCase() &&
          product.destination.toLowerCase() === destination.toLowerCase()
        ) {
          return true;
        } else {
          return false;
        }
      })
      .map((product) => {
        return (
          <Product key={product._id} product={product} curDate={date}></Product>
        );
      });
    setBus(buses);
    dispatch(savecity({ source, destination }));
  };

  const setDateHandler = async (date) => {
    setDate(date);
  }

    useEffect(() => {
      let test = products?.filter((product) => {
        if (product.source === source && product.destination === destination) {
          return true;
        } else {
          return false;
  
        }
      }).map(product => {
        return <Product key={product._id} product={product}></Product>;
      });
      if (test && test.length && !buses && cities["cities"]) {
        searchBusHandler();
      }
  });

  return (

    <div>
    {loading ? (
       <LoadingBox></LoadingBox>
     ) : error ? (
       <MessageBox variant="danger">{error}</MessageBox>
     ) : (

    <div className="row center">
      <div className="searchBar">
        <input
          type="text"
          placeholder="Enter Source"
          value={source}
          onChange={(e) => setSource(e.target.value)}
        />
        <input
          type="text"
          placeholder="Enter Destination"
          value={destination}
          onChange={(e) => setDestination(e.target.value)}
        />
        <input
          type="date"
          placeholder="Select Date"
          value={date}
          min={minDate}
          max={maxDate}
          onChange={(e) => setDateHandler(e.target.value)}
        />
        <button onClick={searchBusHandler}>Search</button>
      </div>

      {source || destination || buses ? null : <HeadingScreen />}

      {buses}
    </div>
     )}
    </div>
 
  );
}
