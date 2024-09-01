import { axios } from "../infra/axios";
import { useEffect, useRef, useState } from "react";
import { useParams } from "react-router-dom";
import RelicCard from "../components/RelicCard";
import { Center, CircularProgress } from "@chakra-ui/react";

export type RelicDetail = {
    relicId: string;
    name: string;
    desc: string;
    relicCode: string;
    sizeInfo: string;
}

export default function Detail() {
    const { relicId } = useParams();
    const [relicDetail, setRelicDetail] = useState<RelicDetail | null>(null);
    const [colorAvailable, setColorAvailable] = useState(false);
    const evtSource = useRef<EventSource | null>(null);


    useEffect(() => {
        const connectSse = () => {
            evtSource.current = new EventSource(`${import.meta.env.VITE_SERVER_BASE_URL}/sse/connect?topic=${relicId}`);
            evtSource.current.addEventListener('completed', (e: MessageEvent) => {
                console.debug('colorize completed', e)
                const data = JSON.parse(e.data)
                if (data?.success) {
                    console.debug('colorize success')
                    setColorAvailable(true);

                    evtSource.current?.close();
                    return;
                }
                console.error('colorize failed')
            })
        }

        const fetchData = async() => {
            const response = await axios.get(`/v1/dryplates/${relicId}`);
            // todo: 예외처리

            await axios.post(`/v1/dryplates/${relicId}/colorize`); // 응답 도착시 흑백 이미지 요청 가능
            setTimeout(() => setRelicDetail(response.data.data), 100);
        }

        connectSse();
        fetchData();
    }, []);

    return (
        (relicDetail != null
            ? <Center marginTop='32'>
                <RelicCard relicId={relicDetail.relicId} name={relicDetail.name} desc={relicDetail.desc} relicCode={relicDetail.relicCode} sizeInfo={relicDetail.sizeInfo} colorAvailable={colorAvailable} />
              </Center>
            : <Center marginTop='256'><CircularProgress isIndeterminate color='green.300' /></Center>
        )
    )
}