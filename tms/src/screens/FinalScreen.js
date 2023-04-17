import React from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";

function FinalScreen() {
  const userSignin = useSelector((state) => state.userSignin);
  const { userInfo } = userSignin;
  return (
    <>
      <h1 className="myHeader">
        Congratulations!!! {userInfo ? userInfo.name : "user"}{" "}
      </h1>
      <h1 className="myHeader">Your Booking Has Been Confirmed</h1>
      <div className="homeLink">
        <Link className="brand1" to="/">
          back to home
        </Link>
      </div>
    </>
  );
}

export default FinalScreen;
