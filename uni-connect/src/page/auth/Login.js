import { useContext, useState } from "react";
import InputField from "../../component/InputField";
import { createFormFieldObject, getFormField, getObjectFromFormFieldObject, processErrors, setFieldError } from "../../util/RequestFormUtil";
import { EMPTY_STRING_REGEX } from "../../constant/Regex";
import { apiLogin } from "../../action/ApiActions";
import { decodeToken } from "../../util/JwtDecodeUtil";
import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";

function Login() {
    const { logIn } = useContext(AuthContext);
    const navigate = useNavigate();
    let [requestForm, setRequestForm] = useState(initForm());

    function initForm(){
        return createFormFieldObject({ username: "", password: "" });
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
            const response=await apiLogin(getObjectFromFormFieldObject(requestForm));
            if (response.status) {
                sessionStorage.setItem("user", JSON.stringify(response.data));
                alert(response.message);
                console.log("Decoded Token",decodeToken(response.data.accessToken))
                setRequestForm(initForm());
                logIn(response.data);
                navigate('/');
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

        if (!EMPTY_STRING_REGEX.test(requestForm.username.value)) {
            reqForm.username = setFieldError(requestForm.username, "Username must not be empty");
            validationSuccess = false;
        }

        if (!EMPTY_STRING_REGEX.test(requestForm.password.value)) {
            reqForm.password = setFieldError(requestForm.password, "Password must not be empty");
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
                <h1>Login</h1>
                <p>Please sign in to continue.</p>
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
                    <button type='submit'>LOGIN</button>
                </div>
            </form>

            <div>
                <p>Don't have an account?   <Link to="/sign-up"> Sign Up.</Link> </p>
            </div>
        </div>
    );
}

export default Login;