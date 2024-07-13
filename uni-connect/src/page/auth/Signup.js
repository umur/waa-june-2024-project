import { useState } from "react";
import InputField from "../../component/InputField";
import { createFormFieldObject, getFormField, getObjectFromFormFieldObject, processErrors, setFieldError } from "../../util/RequestFormUtil";
import { EMAIL_REGEX, EMPTY_STRING_REGEX, PASSWORD_REGEX } from "../../constant/Regex";
import {  apiSignUp } from "../../action/ApiActions";
import { Link } from "react-router-dom";

function SignUp() {


    let [requestForm, setRequestForm] = useState(initForm());

    function initForm() {
        return createFormFieldObject({ email: "", username: "", password: "", confirmPassword: "" })
    }


    const handleChange = (e) => {
        const { name, value } = e.target;
        setRequestForm(prev => ({
            ...prev,
            [name]: getFormField(value),
        }));
    };


    const handleSubmit = async (e) => {
        e.preventDefault();
        if (validateRequestForm()) {
            const response = await apiSignUp(getObjectFromFormFieldObject(requestForm));
            if (response.status) {
                alert(response.message);
                setRequestForm(initForm());
            }
            else {
                if(response.errors){
                    const formWithErrs=processErrors(response.errors,requestForm);
                    setRequestForm({...formWithErrs});
                }
                alert(response.message);
            }
        }
    };

    const validateRequestForm = () => {
        let validationSuccess = true;
        const reqForm = { ...requestForm };

        if (!EMAIL_REGEX.test(requestForm.email.value)) {
            reqForm.email = setFieldError(requestForm.email, "Email format not valid");
            validationSuccess = false;
        }
        if (!EMPTY_STRING_REGEX.test(requestForm.username.value)) {
            reqForm.username = setFieldError(requestForm.username, "Username must not be empty");
            validationSuccess = false;
        }
        if (!PASSWORD_REGEX.test(requestForm.password.value)) {
            reqForm.password = setFieldError(requestForm.password, "Password must be 8-20 characters long and include at least one lowercase letter, one uppercase letter, one number, and one special character.");
            validationSuccess = false;
        }
        if (requestForm.password.value !== requestForm.confirmPassword.value) {
            reqForm.confirmPassword = setFieldError(requestForm.password, "Confirm Password must match password");
            validationSuccess = false;
        }
        if (!validationSuccess) {
            setRequestForm(reqForm);
        }
        return validationSuccess;
    };





    return (
        <div>
            <form onSubmit={handleSubmit}>
                <h1>Sign Up</h1>
                <div>
                    <InputField
                        type="email"
                        name="email"
                        onChange={handleChange}
                        placeholder='Email'
                        label="Email"
                        value={requestForm.email.value}
                        className="form-control"
                    />
                    {requestForm.email.error && <span>{requestForm.email.message}</span>}

                </div>
                <div>
                    <InputField
                        type="username"
                        name="username"
                        onChange={handleChange}
                        placeholder='Username'
                        label="Username"
                        value={requestForm.username.value}
                        className="form-control"
                    />
                    {requestForm.username.error && <span>{requestForm.username.message}</span>}

                </div>

                <div>
                    <InputField
                        type="password"
                        name="password"
                        onChange={handleChange}
                        placeholder='Password'
                        label="Password"
                        value={requestForm.password.value}
                        className="form-control"
                    />
                    {requestForm.password.error && <span>{requestForm.password.message}</span>}


                </div>

                <div>
                    <InputField
                        type="password"
                        name="confirmPassword"
                        onChange={handleChange}
                        placeholder='Confirm Password'
                        label="Confir Password"
                        value={requestForm.confirmPassword.value}
                        className="form-control"
                    />
                    {requestForm.confirmPassword.error && <span>{requestForm.confirmPassword.message}</span>}


                </div>

                <div>
                    <button type='submit'>SIGN UP</button>
                </div>
            </form>

            <div>
                <p>Already have an account?        <Link to="/login"> Sign In.</Link> </p>
            </div>
        </div>
    );
}

export default SignUp;