import {action, makeObservable, observable} from "mobx"
import ErrorMessage from "./ErrorMessage"

export default class ErrorMessageContainer {
    @observable errorMessages = {}
    constructor() {
        makeObservable(this)
    }

    @action merge = (container) => {
        this.errorMessages = {}
        for (let idx in container.messages) {
            const message = container.messages[idx]
            this.errorMessages[message.code] = new ErrorMessage(message.code, message.message)
        }
    }

    hasError = (code) => {
        return this.errorMessages[code] !== undefined
    }

    getFieldError = (code) => {
        return this.hasError(code) ? this.errorMessages[code].value.value : null
    }
}