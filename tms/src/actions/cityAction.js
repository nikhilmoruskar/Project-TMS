export const savecity = (data) => (dispatch) => {
    dispatch({ type: "CITY_CHANGED_REDUCER", payload: data });
  };