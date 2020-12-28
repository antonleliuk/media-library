import {action, observable} from "mobx"

export default class FormValue {
    @observable nativeValue

    constructor(defaultValue) {
        if (!defaultValue) {
            this.setValue('')
        } else {
            this.setValue(defaultValue)
        }
    }

    @action setValue = (newValue) => {
        if (this.nativeValue) {
            this.nativeValue.set(newValue)
        } else {
            this.nativeValue = observable.box(newValue)
        }
    }

    get value() {
        return this.nativeValue.get()
    }

    set value(newValue) {
        this.setValue(newValue)
    }
}

