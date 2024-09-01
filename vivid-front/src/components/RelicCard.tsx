import { Box, Card, CardBody, CardHeader, Heading, Stack, StackDivider, Text, Image, Button, ButtonGroup, Divider } from "@chakra-ui/react";
import { RelicDetail } from "../pages/Detail";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function RelicCard ({ relicId, name, desc, relicCode, sizeInfo, colorAvailable }: RelicDetail & { colorAvailable: boolean }) {
    const [isColor, setIsColor] = useState(colorAvailable);

    const navigate = useNavigate();

    useEffect(() => {
        if (colorAvailable) {
            setIsColor(true);
        }
    }, [colorAvailable]);

    return (
        <Card>
            <CardHeader>
                <Heading size='md'>{name} ({relicCode})</Heading>
            </CardHeader>

            <CardBody>
                <Box>
                    <Image
                        boxSize="500px" src={`${import.meta.env.VITE_SERVER_BASE_URL}/v1/dryplates/${relicId}/image?colorized=${isColor}`}
                    />
                    <ButtonGroup spacing='2' marginTop='4'>
                        {
                            colorAvailable
                            ? <Button variant='solid' onClick={() => setIsColor(!isColor)}>{isColor ? '원본' : '컬러'}</Button>
                            : <Button disabled>채색중...</Button>
                        }
                        <Button variant='ghost' onClick={() => navigate('/')}>
                            돌아가기
                        </Button>
                        <Button variant='ghost' onClick={() => window.open(`https://www.emuseum.go.kr/detail?relicId=${relicId}`, '_blank')}>
                            이뮤지엄에서 보기
                        </Button>
                    </ButtonGroup>
                </Box>

                <Divider marginTop='4' marginBottom='4' />

                <Stack divider={<StackDivider />} spacing='4'>
                <Box>
                    <Heading size='xs' textTransform='uppercase'>
                    설명
                    </Heading>
                    <Text pt='2' fontSize='sm'>
                    {desc}
                    </Text>
                </Box>
                <Box>
                    <Heading size='xs' textTransform='uppercase'>
                    크기
                    </Heading>
                    <Text pt='2' fontSize='sm'>
                    {sizeInfo}
                    </Text>
                </Box>
                </Stack>
            </CardBody>
        </Card>
    )
}