function InputField(props) {
    return ( 
        <input 
          name={props.name} 
          type={props.type} 
          value={props.value} 
          onChange={props.onChange} 
          className={props.className}
          placeholder={props.placeholder}
        />
     );
}

export default InputField;