'use server'

import { redirect } from 'next/navigation';
import { Base64 } from 'js-base64';
import { cookies } from 'next/headers';

export default async function checkAuth() {
    const apikeyEncoded = cookies().get('apikey')?.value;
    if (apikeyEncoded == null) {
        redirect('/login');
    }
    const apikeyDecoded = Base64.decode(apikeyEncoded);
    
    if (apikeyDecoded == null) {
        redirect('/login');
    }

    const myHeaders = new Headers();
    myHeaders.append("apikey", apikeyDecoded);

    const response = await fetch("http://127.0.0.1:8080/user/checkapikey/", {
        method: "GET",
        headers: myHeaders,
        redirect: "follow"
    }).then(response => response.text())
    .then(result => { return result })
    .catch(error => console.log('error', error));

    if (response == "true") {
        return;
    } else {
        redirect('/login');
    }
}
