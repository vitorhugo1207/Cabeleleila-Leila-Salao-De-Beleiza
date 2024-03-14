'use server'

import { Base64 } from 'js-base64';
import { cookies } from 'next/headers'

export default async function getMyHeaders() {
    const apikeyEncoded = cookies().get('apikey')?.value;
    const apikeyDecoded = Base64.decode(apikeyEncoded);

    const myHeaders = new Headers();
    myHeaders.append("apikey", apikeyDecoded);
    return myHeaders;
}
