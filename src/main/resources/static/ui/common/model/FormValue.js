import {action, makeObservable, observable} from "mobx"

export default class FormValue {
    nativeValue

    constructor(defaultValue) {
        makeObservable(this, {
            nativeValue: observable,
            setValue: action
        })
        if (!defaultValue) {
            this.setValue('')
        } else {
            this.setValue(defaultValue)
        }
    }

    setValue = (newValue) => {
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

