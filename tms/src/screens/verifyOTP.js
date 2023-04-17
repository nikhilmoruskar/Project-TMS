import { useState } from "react";

import { useHistory } from "react-router-dom";

const VerifyOtp = () => {
  
  const history = useHistory();
  const [otp, setOtp] = useState("");

  const checkOtp = () => {
    console.log("inside checkotp");
    let data = sessionStorage.getItem("otp");
    console.log(data);
    console.log(otp);
    if (otp == data) {
      history.push("/ResetPass");
    }
  };

  return (
    <div>
      <center>
        <table>
          <tr>
            <td style={{ color: "white" }}>Enter the OTP</td>
            <td>
              <input
                type={"text"}
                placeholder={"Enter Valid OTP "}
                onChange={(e) => {
                  setOtp(e.target.value);
                }}
              ></input>
            </td>
          </tr>
          <tr>
            <td>
              <button onClick={checkOtp}>SendOTP</button>
            </td>
          </tr>
        </table>
      </center>
    </div>
  );
};

export default VerifyOtp;
