export const cityReducer = (
    state = {},
    action
  ) => {
    switch (action.type) {
      case "CITY_CHANGED_REDUCER":
        return {
            cities : action.payload
        }
      default:
        return state;
    }
  };