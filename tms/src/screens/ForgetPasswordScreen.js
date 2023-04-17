import { useState, useEffect } from "react";
//import config from "../config";
import axios from "axios";

//import { toast } from "react-toastify";
//import "../Assets/index.css";
import { useHistory } from "react-router-dom";

//debugger;

function ForgotPasswordScreen() {
  useEffect =
    (() => {
      //  debugger;
    },
    []);

  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const history = useHistory();
  const onsubmit = () => {
    console.log(email);
    const body = {
      email,
      name,
    };

    const serverURL = "http://localhost:8080/forgetPassword";
    const url = serverURL + `/sendotp`;

    axios.post(url, body).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        sessionStorage.setItem("otp", result["otp"]);
        sessionStorage.setItem("name", name);
        sessionStorage.setItem("email", email);
        history.push("/verifyOtp");
        // toast.info("OTP send successfully");
      } else {
        //toast.error(result.error);
      }
    });
  };

  console.log("inside forget");
  return (
    <div>
      <br></br>

      <center>
        <h1>Forget Password</h1>
        <table table-border={0}>
          <br></br>

          <tr>
            <td>
              <h2>Name :</h2>
            </td>
            <td>
              <input
                type={"name"}
                placeholder={"Enter Name"}
                onChange={(e) => {
                  setName(e.target.value);
                }}
              ></input>
            </td>
          </tr>

          <tr>
            <td>
              <h2>Email :</h2>
            </td>
            <td>
              <input
                type={"email"}
                placeholder={"Enter Email"}
                onChange={(e) => {
                  setEmail(e.target.value);
                }}
              ></input>
            </td>
          </tr>
          <br></br>
          <tr>
            <td colSpan={2}>
              <center>
                <button onClick={onsubmit}>Submit</button>
              </center>
            </td>
          </tr>
        </table>
      </center>
    </div>
  );
}
const style = {
  container: {
    width: 700,
    height: 350,
    padding: 2,
    position: "relative",
    top: 150,
    left: 0,
    right: 0,
    bottom: 0,
    margin: "auto",
    borderRadius: 10,
    border: 1,
    borderColor: "#171511",
    boxShadow: "1px 1px 30px 10px #FFEEB8",
  },
  signup: {
    position: "relative",
    width: "40%",
    height: 40,
    backgroundColor: "",
    color: "black",
    borderRadius: 5,
    border: "none",
    marginTop: 10,
    textAlign: "center",
  },
};
export default ForgotPasswordScreen;

// import React, { useEffect, useState } from 'react';
// import { useDispatch, useSelector } from 'react-redux';
// import { forgetPassword } from '../actions/userActions';
// import LoadingBox from '../components/LoadingBox';
// import MessageBox from '../components/MessageBox';

// export default function ForgetPasswordScreen(props) {

//   const [mobileNumber,setMobileNumber] = useState('');
//   const [password, setPassword] = useState('');

//   const redirect = props.location.search
//     ? props.location.search.split('=')[1]
//     : '/';

//   const userForgetPassword = useSelector((state) => state.userForgetPassword);
//   const { userInfo, loading, error } = userForgetPassword;

//   const dispatch = useDispatch();
//   const submitHandler = (e) => {
//     e.preventDefault();
//       dispatch(forgetPassword(mobileNumber,password));
//   };
//   useEffect(() => {
//     if (userInfo) {
//       props.history.push(redirect);
//     }
//   }, [props.history, redirect, userInfo]);
//   return (
//     <div>
//       <form className="form" onSubmit={submitHandler}>
//         <div>
//           <h1 style={{color:'white'}}>Forget Password</h1>
//         </div>
//         {loading && <LoadingBox></LoadingBox>}
//         {error && <MessageBox variant="danger">{error}</MessageBox>}
//         <div>
//           <label htmlFor="mobileNumber" style={{color:'white'}}>Enter mobile number for Security check</label>
//           <input
//             type="tel"
//             id="mobileNumber"
//             placeholder="Enter Mobile Number"
//             required
//             onChange={(e) => setMobileNumber(e.target.value)}
//           ></input>
//         </div>

//         <div>
//           <label htmlFor="password" style={{color:'white'}}>New Password</label>
//           <input
//             type="password"
//             id="password"
//             placeholder="Enter New password"
//             required
//             onChange={(e) => setPassword(e.target.value)}
//           ></input>
//         </div>

//         <div>
//           <label />
//           <button className="primary" type="submit">
//             Submit
//           </button>
//         </div>
//       </form>
//     </div>
//   );
// }
