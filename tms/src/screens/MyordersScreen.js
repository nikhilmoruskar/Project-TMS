import React, { useState, useEffect } from "react";
import axios from "axios";
import MessageBox from "../components/MessageBox";
import { useSelector } from "react-redux";

function Myorders() {

  const userSignin = useSelector((state) => state.userSignin);
  const { userInfo } = userSignin;
  let [orders, setOrders] = useState([]);

  useEffect(() => {
    if (userInfo) {
      axios
        .get("http://localhost:8080/user/orders/" + userInfo["_id"])
        .then(response => setOrders(response.data));
    }
  }, [userInfo]);

  return (
    <div className="col-2">
      <h1>Order Details</h1>
      {orders.length === 0 ? (
        <MessageBox>
          You don't have any orders
        </MessageBox>
      ) : (
        <table className="table">
          <thead>
            <tr>
              <th>Bus</th>
              <th>Name</th>
              <th>Source</th>
              <th>Destination</th>
              <th>Date</th>
              <th>Number of Tickets</th>
              <th>Total bill</th>
            </tr>
          </thead>
          <tbody>
            {orders.map((order) => (
              <tr key={order._id}>
                <td><img
                  src={order.images}
                  alt={order.name}
                  className="small"
                ></img></td>
                <td>{order.name}</td>
                <td>{order.source}</td>
                <td>{order.destination}</td>
                <td>{order.date}</td>
                <td>{order.qty}</td>
                <td>₹{order.price * order.qty}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default Myorders;
