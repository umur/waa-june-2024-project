function InputField(props) {
    return ( 
      <div className="form-group row mb-3"> 
      <label className="col-sm-2 col-form-label">{props.label}</label>
      <div className="col-sm-10">
        <input 
          name={props.name} 
          type={props.type} 
          value={props.value} 
          onChange={props.onChange} 
          className={props.className}
          placeholder={props.placeholder}
        />
      </div>
    </div>
     );
}

export default InputField;