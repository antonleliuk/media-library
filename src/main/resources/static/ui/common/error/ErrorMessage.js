import {observable} from "mobx"
import FormValue from "../model/FormValue"

export default class ErrorMessage {
    @observable code
    @observable value

    constructor(code, value) {
        this.code = new FormValue(code)
        this.value = new FormValue(value)
    }

}