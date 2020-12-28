export default class FetchService {
    contextPath

    constructor() {
        this.contextPath = window.globalContextRoot
    }

    submitForm = async (url, data) => {
        try {
            const response = await fetch(`${this.contextPath}${url}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: data
            })
            if (response.ok) {
                return await response.json()
            }
            // handle response not ok
        } catch (e) {
            // handle error
        }
    }

    post = async (url, data) => {
        try {
            const response = await fetch(`${this.contextPath}${url}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: data
            })
            if (response.ok) {
                return await response.json()
            }
            // handle response not ok
        } catch (e) {
            // handle errors
        }
    }

}