import { Button, NumberDecrementStepper, NumberIncrementStepper, NumberInput, NumberInputField, NumberInputStepper } from '@chakra-ui/react';
import './Main.css'
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function Main() {
    const RELIC_NUM_MIN = 1
    const RELIC_NUM_MAX = 38138

    const [relicNum, setRelicNum] = useState('1');
    const navigate = useNavigate();

    useEffect(() => {
        const random = Math.floor(Math.random() * RELIC_NUM_MAX + RELIC_NUM_MIN);
        setRelicNum(String(random));
    }, []);

    const toPsCode = (relicNum: string) => {
        return 'PS010010010200' + relicNum.padStart(5, '0') + '00000';
    }

    return (
        <div className="center-container">
            <div className="input-container">
                <span className="label-span">건판</span>

                <NumberInput value={relicNum} min={1} max={38138} onChange={(val) => setRelicNum(val)}>
                    <NumberInputField />
                    <NumberInputStepper>
                        <NumberIncrementStepper />
                        <NumberDecrementStepper />
                    </NumberInputStepper>
                </NumberInput> 

                <div className="btn-container">
                    <Button onClick={() => navigate("/" + toPsCode(relicNum))}>보기</Button>
                </div>
            </div>        
        </div>
    );
}
