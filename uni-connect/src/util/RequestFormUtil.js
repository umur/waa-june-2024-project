export function createFormFieldObject(input) {
    const output = {};
    for (const key in input) {
      if (Object.prototype.hasOwnProperty.call(input, key)) {
        output[key] = {
          message: "",
          error: false,
          value: input[key],
        };
      }
    }
  
    return output;
  }
  
  export function getObjectFromFormFieldObject(input) {
    const output = {};
    for (const key in input) {
      if (Object.prototype.hasOwnProperty.call(input, key)) {
        output[key] = input[key].value;
      }
    }
  
    return output;
  }
  
  export function getFormField(input) {
    const output = { value: input, error: false, message: "" };
    return output;
  }
  
  export function setFieldError(input, errorMessage) {
    const output = { ...input, error: true, message: errorMessage };
    return output;
  }

  export function getErrorResponseObject(msg) {
    const output = {
        status: false,
        code: "500",
        message: "Oops something went wrong. Please retry later ("+msg+")",
        data: null
    };
    return output;
}

export function processErrors(errors, formFieldObject) {
  if (errors && errors.fields) {
    errors.fields.forEach((error) => {
      const field = error.field;
      const messages = error.messages.join(", ");
      if (formFieldObject[field]) {
        formFieldObject[field].message = messages;
        formFieldObject[field].error = true;
      }
    });
  }
  return formFieldObject;
}



  
  